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
var http_client_1 = require('../service/http.client');
var RestfulService = (function () {
    function RestfulService(httpClient) {
        this.httpClient = httpClient;
        this.listUser = function () {
            return this.httpClient.get(HOST + "/odoo/admin/user/list.do");
        };
        this.saveUser = function (user) {
            return this.httpClient.post(HOST + "/odoo/admin/user/save.do", JSON.stringify(user));
        };
        this.listEpicLinks = function () {
            return this.httpClient.get(HOST + "/odoo/admin/epicLink/list.do");
        };
        this.saveEpicLink = function (epicLink) {
            return this.httpClient.post(HOST + "/odoo/admin/epicLink/save.do", JSON.stringify(epicLink));
        };
        this.listVacationDates = function (year) {
            return this.httpClient.get(HOST + "/odoo/admin/vacationDate/list/" + year + ".do");
        };
        this.saveVacationDate = function (vacationDate) {
            return this.httpClient.post(HOST + "/odoo/admin/vacationDate/save.do", JSON.stringify(vacationDate));
        };
        this.deleteVacationDate = function (date) {
            return this.httpClient.post(HOST + "/odoo/admin/vacationDate/delete.do", JSON.stringify(date));
        };
        this.listCalendar = function (year, month) {
            return this.httpClient.get(HOST + "/odoo/admin/calendar/list/" + year + "/" + month + ".do");
        };
    }
    RestfulService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_client_1.HttpClient])
    ], RestfulService);
    return RestfulService;
}());
exports.RestfulService = RestfulService;
// var HOST = "http://172.17.50.8:8088";
var HOST = "http://localhost:8080";
//# sourceMappingURL=restful.service.js.map