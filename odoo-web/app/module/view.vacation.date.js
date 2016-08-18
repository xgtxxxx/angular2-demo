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
var VacationDateComponent = (function () {
    function VacationDateComponent(restfulService, parent) {
        this.restfulService = restfulService;
        this.parent = parent;
        this.vacationDates = [];
        this.editVacationDate = {};
        this.types = [{
                id: 1,
                name: 'Vacation'
            }, {
                id: 2,
                name: 'Work Day'
            }];
        this.refresh = function () {
            var component = this;
            var date = new Date();
            var year = date.getFullYear();
            this.restfulService.listVacationDates(year).subscribe(function (res) {
                console.log(res.json());
                component.vacationDates = res.json();
            });
        };
        this.newVacationDate = function () {
            this.editVacationDate = {};
            $("#newAddModal").modal("show");
        };
        this.doSave = function () {
            var component = this;
            this.restfulService.saveVacationDate(this.editVacationDate).subscribe(function (res) {
                if (res.json().key == 'success') {
                    $("#newAddModal").modal("hide");
                    component.refresh();
                }
            });
        };
        this.delete = function (date) {
            var component = this;
            this.restfulService.deleteVacationDate(date).subscribe(function (res) {
                if (res.json().key == 'success') {
                    $("#newAddModal").modal("hide");
                    component.refresh();
                }
            });
        };
    }
    VacationDateComponent.prototype.ngOnInit = function () {
        this.parent.setActiveByPath(this.parent.vacationDates);
        this.refresh();
    };
    ;
    VacationDateComponent = __decorate([
        core_1.Component({
            selector: 'app-vacation-date',
            templateUrl: "views/vacation-date.html"
        }), 
        __metadata('design:paramtypes', [restful_service_1.RestfulService, app_component_1.AppComponent])
    ], VacationDateComponent);
    return VacationDateComponent;
}());
exports.VacationDateComponent = VacationDateComponent;
//# sourceMappingURL=view.vacation.date.js.map