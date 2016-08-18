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
        this.refresh = function () {
            var component = this;
            this.restfulService.listCalendar(this.year, this.month).subscribe(function (res) {
                console.log(res.json());
                component.weeks = res.json();
                if (component.month < 10) {
                    component.symbol = "0";
                }
                else {
                    component.symbol = "";
                }
            });
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