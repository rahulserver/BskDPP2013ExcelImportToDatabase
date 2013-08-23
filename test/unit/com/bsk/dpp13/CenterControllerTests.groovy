package com.bsk.dpp13



import org.junit.*
import grails.test.mixin.*

@TestFor(CenterController)
@Mock(Center)
class CenterControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/center/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.centerInstanceList.size() == 0
        assert model.centerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.centerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.centerInstance != null
        assert view == '/center/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/center/show/1'
        assert controller.flash.message != null
        assert Center.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/center/list'

        populateValidParams(params)
        def center = new Center(params)

        assert center.save() != null

        params.id = center.id

        def model = controller.show()

        assert model.centerInstance == center
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/center/list'

        populateValidParams(params)
        def center = new Center(params)

        assert center.save() != null

        params.id = center.id

        def model = controller.edit()

        assert model.centerInstance == center
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/center/list'

        response.reset()

        populateValidParams(params)
        def center = new Center(params)

        assert center.save() != null

        // test invalid parameters in update
        params.id = center.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/center/edit"
        assert model.centerInstance != null

        center.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/center/show/$center.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        center.clearErrors()

        populateValidParams(params)
        params.id = center.id
        params.version = -1
        controller.update()

        assert view == "/center/edit"
        assert model.centerInstance != null
        assert model.centerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/center/list'

        response.reset()

        populateValidParams(params)
        def center = new Center(params)

        assert center.save() != null
        assert Center.count() == 1

        params.id = center.id

        controller.delete()

        assert Center.count() == 0
        assert Center.get(center.id) == null
        assert response.redirectedUrl == '/center/list'
    }
}
