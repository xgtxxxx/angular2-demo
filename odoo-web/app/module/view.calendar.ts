import { Component, OnInit } from '@angular/core';
import { RestfulService } from '../service/restful.service';
import { Response } from '@angular/http';
import {AppComponent} from '../app.component';
@Component({
    selector: 'app-calendar',
    templateUrl:"views/calendar.html"
})

export class CalendarComponent implements OnInit{
    constructor (private restfulService: RestfulService, private parent: AppComponent) {}
    weeks = [];
    month = 0;
    year = 0;
    symbol = "";
    ngOnInit(){
        this.parent.setActiveByPath(this.parent.calendar);
        var date = new Date();
        this.year = date.getFullYear();
        this.month = date.getMonth()+1;
        this.refresh();
    };
    refresh = function(){
        var component = this;
        this.restfulService.listCalendar(this.year, this.month).subscribe(function (res:Response) {
            console.log(res.json());
            component.weeks = res.json();
            if(component.month<10){
                component.symbol = "0";
            }else{
                component.symbol = "";
            }
        });
    }

    prev = function(){
        if(this.month==1){
            this.year = this.year-1;
            this.month = 12;
        }else{
            this.month = this.month-1;
        }
        this.refresh();
    }

    next = function(){
        if(this.month==12){
            this.year = this.year+1;
            this.month = 1;
        }else{
            this.month = this.month+1;
        }
        this.refresh();
    }
}

