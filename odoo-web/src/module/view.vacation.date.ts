import { Component, OnInit } from '@angular/core';
import { RestfulService } from '../service/restful.service';
import { Response } from '@angular/http';
import {AppComponent} from '../app.component';

@Component({
    selector: 'app-vacation-date',
    templateUrl:"../views/vacation-date.html"
})

export class VacationDateComponent implements OnInit{
    constructor (private restfulService: RestfulService, private parent: AppComponent) {}
    vacationDates = [];
    editVacationDate = {};
    types = [{
        id : 1,
        name : 'Vacation'
    },{
        id : 2,
        name : 'Work Day'
    }];
    ngOnInit(){
        this.parent.setActiveByPath(this.parent.vacationDates);
        this.refresh();
    };
    refresh = function(){
        var component = this;
        var date = new Date();
        var year = date.getFullYear();
        this.restfulService.listVacationDates(year, function(res){
            component.vacationDates = res;
        });
    }

    newVacationDate = function(){
        this.editVacationDate = {};
        $("#newAddModal").modal("show");
    }

    doSave = function(){
        var component = this;
        this.restfulService.saveVacationDate(this.editVacationDate, function(res){
            if(res.key=='success'){
                $("#newAddModal").modal("hide");
                component.refresh();
            }
        });
    }

    delete = function(date){
        var component = this;
        this.restfulService.deleteVacationDate(date, function(res){
            if(res.key=='success'){
                $("#newAddModal").modal("hide");
                component.refresh();
            }
        });
    }
}

