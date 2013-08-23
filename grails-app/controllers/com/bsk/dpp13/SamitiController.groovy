package com.bsk.dpp13

import org.springframework.dao.DataIntegrityViolationException

class SamitiController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [samitiInstanceList: Samiti.list(params), samitiInstanceTotal: Samiti.count()]
    }

    def create() {
        [samitiInstance: new Samiti(params)]
    }

    def save() {
        def samitiInstance = new Samiti(params)
        if (!samitiInstance.save(flush: true)) {
            render(view: "create", model: [samitiInstance: samitiInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'samiti.label', default: 'Samiti'), samitiInstance.id])
        redirect(action: "show", id: samitiInstance.id)
    }

    def show(Long id) {
        def samitiInstance = Samiti.get(id)
        if (!samitiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'samiti.label', default: 'Samiti'), id])
            redirect(action: "list")
            return
        }

        [samitiInstance: samitiInstance]
    }

    def edit(Long id) {
        def samitiInstance = Samiti.get(id)
        if (!samitiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'samiti.label', default: 'Samiti'), id])
            redirect(action: "list")
            return
        }

        [samitiInstance: samitiInstance]
    }

    def update(Long id, Long version) {
        def samitiInstance = Samiti.get(id)
        if (!samitiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'samiti.label', default: 'Samiti'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (samitiInstance.version > version) {
                samitiInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'samiti.label', default: 'Samiti')] as Object[],
                        "Another user has updated this Samiti while you were editing")
                render(view: "edit", model: [samitiInstance: samitiInstance])
                return
            }
        }

        samitiInstance.properties = params

        if (!samitiInstance.save(flush: true)) {
            render(view: "edit", model: [samitiInstance: samitiInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'samiti.label', default: 'Samiti'), samitiInstance.id])
        redirect(action: "show", id: samitiInstance.id)
    }

    def delete(Long id) {
        def samitiInstance = Samiti.get(id)
        if (!samitiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'samiti.label', default: 'Samiti'), id])
            redirect(action: "list")
            return
        }

        try {
            samitiInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'samiti.label', default: 'Samiti'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'samiti.label', default: 'Samiti'), id])
            redirect(action: "show", id: id)
        }
    }
    def listAllAggregatedSamitiWise(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        [samitiInstanceList: Samiti.list(params), samitiInstanceTotal: Samiti.count(),cl3Total: Samiti.first().aggregatedReportMap]
    }

}
