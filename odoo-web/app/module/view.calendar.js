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
var CalendarComponent = (function () {
    function CalendarComponent(restfulService, parent) {
        this.restfulService = restfulService;
        this.parent = parent;
        this.weeks = [];
        this.month = 0;
        this.year = 0;
        this.symbol = "";
        this.users = [];
        this.currentDate = "";
        this.epicLinks = [];
        this.editUser = {
            customs: {
                projects: []
            }
        };
        this.tasks = ["Build", "Testing", "Plan"];
        this.refresh = function () {
            var component = this;
            this.restfulService.listCalendar(this.year, this.month, function (res) {
                console.log(res);
                component.weeks = res;
                if (component.month < 10) {
                    component.symbol = "0";
                }
                else {
                    component.symbol = "";
                }
            });
            var d = new Date();
            var m = d.getMonth() + 1;
            var ms = '';
            if (m < 10) {
                ms = '0' + m;
            }
            else {
                ms = '' + m;
            }
            this.currentDate = d.getFullYear() + '-' + ms + '-' + d.getDate();
            this.getTasks(this.currentDate);
            this.getEpicLinks();
        };
        this.addTask = function (user) {
            this.editUser = user;
            $("#newAddModal").modal("show");
        };
        this.add = function () {
            this.editUser.customs.projects.push({});
        };
        this.del = function (project) {
            var projects = [];
            for (var i = 0; i < this.editUser.customs.projects.length; i++) {
                if (this.editUser.customs.projects[i] != project) {
                    projects.push(this.editUser.customs.projects[i]);
                }
            }
            this.editUser.customs.projects = projects;
            if (projects.length == 0) {
                this.editUser.customs.active = false;
            }
        };
        this.save = function () {
            var component = this;
            this.restfulService.saveCalendarTasks(this.currentDate, this.users, function (res) {
                if (res.key == 'success') {
                    $("#newAddModal").modal("hide");
                    component.refresh();
                }
            });
        };
        this.getEpicLinks = function () {
            var component = this;
            this.restfulService.listEpicLinks(function (res) {
                component.epicLinks = res;
            });
        };
        this.getTasks = function (date) {
            var component = this;
            component.currentDate = date;
            this.restfulService.listCalendarTasks(date, function (res) {
                component.users = res;
            });
        };
        this.formatTasks = function (projects) {
            var task = "";
            for (var i = 0; i < projects.length; i++) {
                if (projects[i].project) {
                    if (i > 0) {
                        task += ",";
                    }
                    task += projects[i].project + "[" + projects[i].task + "] " + projects[i].hours + "h";
                }
            }
            return task;
        };
        this.prev = function () {
            if (this.month == 1) {
                this.year = this.year - 1;
                this.month = 12;
            }
            else {
                this.month = this.month - 1;
            }
            this.refresh();
        };
        this.next = function () {
            if (this.month == 12) {
                this.year = this.year + 1;
                this.month = 1;
            }
            else {
                this.month = this.month + 1;
            }
            this.refresh();
        };
    }
    CalendarComponent.prototype.ngOnInit = function () {
        this.parent.setActiveByPath(this.parent.calendar);
        var date = new Date();
        this.year = date.getFullYear();
        this.month = date.getMonth() + 1;
        this.refresh();
    };
    ;
    CalendarComponent = __decorate([
        core_1.Component({
            selector: 'app-calendar',
            templateUrl: "views/calendar.html"
        }), 
        __metadata('design:paramtypes', [restful_service_1.RestfulService, app_component_1.AppComponent])
    ], CalendarComponent);
    return CalendarComponent;
}());
exports.CalendarComponent = CalendarComponent;
//# sourceMappingURL=view.calendar.js.map