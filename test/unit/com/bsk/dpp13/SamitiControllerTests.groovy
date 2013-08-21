package com.bsk.dpp13



import org.junit.*
import grails.test.mixin.*

@TestFor(SamitiController)
@Mock(Samiti)
class SamitiControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/samiti/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.samitiInstanceList.size() == 0
        assert model.samitiInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.samitiInstance != null
    }

    void testSave() {
        controller.save()

        assert model.samitiInstance != null
        assert view == '/samiti/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/samiti/show/1'
        assert controller.flash.message != null
        assert Samiti.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/samiti/list'

        populateValidParams(params)
        def samiti = new Samiti(params)

        assert samiti.save() != null

        params.id = samiti.id

        def model = controller.show()

        assert model.samitiInstance == samiti
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/samiti/list'

        populateValidParams(params)
        def samiti = new Samiti(params)

        assert samiti.save() != null

        params.id = samiti.id

        def model = controller.edit()

        assert model.samitiInstance == samiti
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/samiti/list'

        response.reset()

        populateValidParams(params)
        def samiti = new Samiti(params)

        assert samiti.save() != null

        // test invalid parameters in update
        params.id = samiti.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/samiti/edit"
        assert model.samitiInstance != null

        samiti.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/samiti/show/$samiti.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        samiti.clearErrors()

        populateValidParams(params)
        params.id = samiti.id
        params.version = -1
        controller.update()

        assert view == "/samiti/edit"
        assert model.samitiInstance != null
        assert model.samitiInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/samiti/list'

        response.reset()

        populateValidParams(params)
        def samiti = new Samiti(params)

        assert samiti.save() != null
        assert Samiti.count() == 1

        params.id = samiti.id

        controller.delete()

        assert Samiti.count() == 0
        assert Samiti.get(samiti.id) == null
        assert response.redirectedUrl == '/samiti/list'
    }
}
