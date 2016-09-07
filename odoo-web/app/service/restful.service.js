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
var mock_data_1 = require('../mock/mock.data');
var RestfulService = (function () {
    function RestfulService(httpClient, mockData) {
        this.httpClient = httpClient;
        this.mockData = mockData;
        this.listUser = function (callback) {
            if (isDemo) {
                return this.mockData.listUser(callback);
            }
            else {
                return this.httpClient.get(HOST + "/odoo/admin/user/list.do", callback);
            }
        };
        this.saveUser = function (user, callback) {
            if (isDemo) {
                return this.mockData.saveUser(user, callback);
            }
            else {
                return this.httpClient.post(HOST + "/odoo/admin/user/save.do", JSON.stringify(user), callback);
            }
        };
        this.listEpicLinks = function (callback) {
            if (isDemo) {
                return this.mockData.listEpicLinks(callback);
            }
            else {
                return this.httpClient.get(HOST + "/odoo/admin/epicLink/list.do", callback);
            }
        };
        this.saveEpicLink = function (epicLink, callback) {
            if (isDemo) {
                return this.mockData.saveEpicLink(epicLink, callback);
            }
            else {
                return this.httpClient.post(HOST + "/odoo/admin/epicLink/save.do", JSON.stringify(epicLink), callback);
            }
        };
        this.listVacationDates = function (year, callback) {
            if (isDemo) {
                return this.mockData.listVacationDates(year, callback);
            }
            else {
                return this.httpClient.get(HOST + "/odoo/admin/vacationDate/list/" + year + ".do", callback);
            }
        };
        this.saveVacationDate = function (vacationDate, callback) {
            if (isDemo) {
                return this.mockData.saveVacationDate(vacationDate, callback);
            }
            else {
                return this.httpClient.post(HOST + "/odoo/admin/vacationDate/save.do", JSON.stringify(vacationDate), callback);
            }
        };
        this.deleteVacationDate = function (date, callback) {
            if (isDemo) {
                return this.mockData.deleteVacationDate(date, callback);
            }
            else {
                return this.httpClient.post(HOST + "/odoo/admin/vacationDate/delete.do", JSON.stringify(date), callback);
            }
        };
        this.listCalendar = function (year, month, callback) {
            if (isDemo) {
                return this.mockData.listCalendar(year, month, callback);
            }
            else {
                return this.httpClient.get(HOST + "/odoo/admin/calendar/list/" + year + "/" + month + ".do", callback);
            }
        };
        this.listCalendarTasks = function (date, callback) {
            if (isDemo) {
                return this.mockData.listCalendarTasks(date, callback);
            }
            else {
                return this.httpClient.get(HOST + "/odoo/admin/calendar/tasks/" + date + ".do", callback);
            }
        };
        this.saveCalendarTasks = function (date, users, callback) {
            if (isDemo) {
                return this.mockData.saveCalendarTasks(date, users, callback);
            }
            else {
                return this.httpClient.post(HOST + "/odoo/admin/calendar/tasks/save/" + date + ".do", JSON.stringify(users), callback);
            }
        };
        this.listScrumblrUser = function (callback) {
            if (isDemo) {
                return this.mockData.listScrumblrUser(callback);
            }
            else {
                return this.httpClient.get(HOST + "/odoo/admin/scrumblr/users.do", callback);
            }
        };
        this.saveScrumblrUser = function (user, callback) {
            if (isDemo) {
                return this.mockData.saveScrumblrUser(user, callback);
            }
            else {
                return this.httpClient.post(HOST + "/odoo/admin/scrumblr/user/save.do", JSON.stringify(user), callback);
            }
        };
        this.deleteScrumblrUser = function (user, callback) {
            if (isDemo) {
                return this.mockData.deleteScrumblrUser(user, callback);
            }
            else {
                return this.httpClient.post(HOST + "/odoo/admin/scrumblr/user/delete.do", JSON.stringify(user), callback);
            }
        };
        this.login = function (account, callback) {
            if (isDemo) {
                return this.mockData.login(account, callback);
            }
            else {
                return this.httpClient.post(HOST + "/odoo/admin/user/login.do", JSON.stringify(account), callback, true);
            }
        };
    }
    RestfulService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_client_1.HttpClient, mock_data_1.MockData])
    ], RestfulService);
    return RestfulService;
}());
exports.RestfulService = RestfulService;
var isDemo = true;
var HOST = "http://172.17.50.8:8088";
// var HOST = "http://localhost:8080"; 
//# sourceMappingURL=restful.service.js.map