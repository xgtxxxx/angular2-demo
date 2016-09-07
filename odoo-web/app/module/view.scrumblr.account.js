"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var restful_service_1 = require('../service/restful.service');
var app_component_1 = require('../app.component');
var ScrumblrAccountComponent = (function () {
    function ScrumblrAccountComponent(restfulService, parent) {
        this.restfulService = restfulService;
        this.parent = parent;
        this.users = [];
        this.editUser = {
            points: 0
        };
        this.authorities = [{ id: 1, name: 'Member' }, { id: 9, name: 'Admin' }];
        this.refresh = function () {
            var component = this;
            this.restfulService.listScrumblrUser(function (res) {
                component.users = res;
            });
        };
        this.newUser = function () {
            this.editUser = { points: 0 };
            $("#newAddModal").modal("show");
        };
        this.edit = function (user) {
            this.editUser = JSON.parse(JSON.stringify(user));
            $("#newAddModal").modal("show");
        };
        this.doSave = function () {
            var component = this;
            this.restfulService.saveScrumblrUser(this.editUser, function (res) {
                if (res.key == 'success') {
                    $("#newAddModal").modal("hide");
                    component.refresh();
                }
            });
        };
        this.del = function (user) {
            if (confirm("Sure to delete it?")) {
                var component = this;
                this.restfulService.deleteScrumblrUser(user, function (res) {
                    if (res.key == 'success') {
                        component.refresh();
                    }
                });
            }
        };
    }
    ScrumblrAccountComponent.prototype.ngOnInit = function () {
        this.parent.setActiveByPath(this.parent.account);
        this.refresh();
    };
    ;
    ScrumblrAccountComponent = __decorate([
        core_1.Component({
            selector: 'app-scrumblr-account',
            templateUrl: "views/scrumblr-account.html"
        }), 
        __metadata('design:paramtypes', [restful_service_1.RestfulService, app_component_1.AppComponent])
    ], ScrumblrAccountComponent);
    return ScrumblrAccountComponent;
}());
exports.ScrumblrAccountComponent = ScrumblrAccountComponent;
//# sourceMappingURL=view.scrumblr.account.js.map