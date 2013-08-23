package com.bsk.dpp13

import org.springframework.dao.DataIntegrityViolationException

class CenterController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [centerInstanceList: Center.list(params), centerInstanceTotal: Center.count()]
    }

    def create() {
        [centerInstance: new Center(params)]
    }

    def save() {
        def centerInstance = new Center(params)
        if (!centerInstance.save(flush: true)) {
            render(view: "create", model: [centerInstance: centerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'center.label', default: 'Center'), centerInstance.id])
        redirect(action: "show", id: centerInstance.id)
    }

    def show(Long id) {
        def centerInstance = Center.get(id)
        if (!centerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'center.label', default: 'Center'), id])
            redirect(action: "list")
            return
        }

        [centerInstance: centerInstance]
    }

    def edit(Long id) {
        def centerInstance = Center.get(id)
        if (!centerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'center.label', default: 'Center'), id])
            redirect(action: "list")
            return
        }

        [centerInstance: centerInstance]
    }

    def update(Long id, Long version) {
        def centerInstance = Center.get(id)
        if (!centerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'center.label', default: 'Center'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (centerInstance.version > version) {
                centerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'center.label', default: 'Center')] as Object[],
                        "Another user has updated this Center while you were editing")
                render(view: "edit", model: [centerInstance: centerInstance])
                return
            }
        }

        centerInstance.properties = params

        if (!centerInstance.save(flush: true)) {
            render(view: "edit", model: [centerInstance: centerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'center.label', default: 'Center'), centerInstance.id])
        redirect(action: "show", id: centerInstance.id)
    }

    def delete(Long id) {
        def centerInstance = Center.get(id)
        if (!centerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'center.label', default: 'Center'), id])
            redirect(action: "list")
            return
        }

        try {
            centerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'center.label', default: 'Center'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'center.label', default: 'Center'), id])
            redirect(action: "show", id: id)
        }
    }
}
