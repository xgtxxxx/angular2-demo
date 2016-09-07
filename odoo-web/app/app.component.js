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
var AppComponent = (function () {
    function AppComponent() {
        this.vacationDates = "/vacation-dates";
        this.epicLinks = "/epic-links";
        this.users = "/users";
        this.calendar = "/calendar";
        this.account = "/account";
        this.loginUser = {};
        this.hello = "";
        this.routers = [
            { href: this.calendar, name: "calendar" },
            { href: this.users, name: "users" },
            { href: this.epicLinks, name: "epic links" },
            { href: this.vacationDates, name: "vacation dates" },
            { href: this.account, name: "Scrumblr account" }
        ];
        this.setActiveByPath = function (path) {
            for (var i = 0; i < this.routers.length; i++) {
                if (this.routers[i].active) {
                    this.routers[i].active = false;
                    break;
                }
            }
            for (var i = 0; i < this.routers.length; i++) {
                var router = this.routers[i];
                if (router.href == path) {
                    router.active = true;
                }
            }
        };
        this.getQueryString = function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null)
                return r[2];
            return null;
        };
    }
    AppComponent.prototype.ngOnInit = function () {
        var name = this.getQueryString("account");
        var password = this.getQueryString("pwd");
        this.loginUser = {
            uname: name,
            pwd: password
        };
        if (name) {
            this.hello = name;
        }
        else {
            this.hello = sessionStorage.getItem("name");
        }
    };
    ;
    AppComponent = __decorate([
        core_1.Component({
            selector: 'my-app',
            templateUrl: "/views/home.html"
        }), 
        __metadata('design:paramtypes', [])
    ], AppComponent);
    return AppComponent;
}());
exports.AppComponent = AppComponent;
//# sourceMappingURL=app.component.js.map