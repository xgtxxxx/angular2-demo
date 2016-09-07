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
var router_1 = require('@angular/router');
var restful_service_1 = require('../service/restful.service');
var app_component_1 = require('../app.component');
var LoginComponent = (function () {
    function LoginComponent(restfulService, parent, _router) {
        this.restfulService = restfulService;
        this.parent = parent;
        this._router = _router;
        this.login = function () {
            var name = this.parent.loginUser.uname;
            if (!name) {
                var token = sessionStorage.getItem("token");
                if (token) {
                    $("#newAddModal").modal("hide");
                    this._router.navigate(['calendar', {}]);
                }
                else {
                    window.location.href = "/access.html";
                }
            }
            else {
                sessionStorage.clear();
                var component = this;
                console.log(this.parent.loginUser);
                this.restfulService.login(this.parent.loginUser, function (res) {
                    var result = res;
                    if (result.success) {
                        $("#newAddModal").modal("hide");
                        sessionStorage.setItem("token", result.token);
                        sessionStorage.setItem("name", name);
                        component._router.navigate(['calendar', {}]);
                    }
                    else {
                        window.location.href = "/access.html";
                    }
                });
            }
        };
    }
    LoginComponent.prototype.ngOnInit = function () {
        $("#newAddModal").modal("show");
        this.login();
    };
    ;
    LoginComponent = __decorate([
        core_1.Component({
            selector: 'app-login',
            templateUrl: 'views/login.html'
        }), 
        __metadata('design:paramtypes', [restful_service_1.RestfulService, app_component_1.AppComponent, router_1.Router])
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=view.login.js.map