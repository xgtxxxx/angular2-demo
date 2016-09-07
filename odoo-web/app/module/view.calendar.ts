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
    users = [];
    currentDate = "";
    epicLinks = [];
    editUser = {
        customs : {
            projects : []
        }
    };
    tasks = ["Build","Testing","Plan"];
    ngOnInit(){
        this.parent.setActiveByPath(this.parent.calendar);
        var date = new Date();
        this.year = date.getFullYear();
        this.month = date.getMonth()+1;
        this.refresh();
    };
    refresh = function(){
        var component = this;
        this.restfulService.listCalendar(this.year, this.month, function(res){
            console.log(res);
            component.weeks = res;
            if(component.month<10){
                component.symbol = "0";
            }else{
                component.symbol = "";
            }
        });
        var d = new Date();
        var m = d.getMonth()+1;
        var ms = '';
        if(m<10){
            ms = '0'+m;
        }else{
            ms = ''+m;
        }
        this.currentDate = d.getFullYear()+'-'+ms+'-'+d.getDate();
        this.getTasks(this.currentDate);
        this.getEpicLinks();
    }
    addTask = function(user){
        this.editUser = user;
        $("#newAddModal").modal("show");
    }
    add = function(){
        this.editUser.customs.projects.push({});
    }
    del = function(project){
        var projects = [];
        for(var i=0; i<this.editUser.customs.projects.length; i++){
            if(this.editUser.customs.projects[i]!=project){
                projects.push(this.editUser.customs.projects[i]);
            }
        }
        this.editUser.customs.projects = projects;
        if(projects.length==0){
            this.editUser.customs.active=false;
        }
    }
    
    save = function(){
        var component = this;
        this.restfulService.saveCalendarTasks(this.currentDate, this.users, function(res){
            if(res.key=='success'){
                $("#newAddModal").modal("hide");
                component.refresh();
            }
        });
    }

    getEpicLinks = function(){
        var component = this;
        this.restfulService.listEpicLinks(function(res){
            component.epicLinks = res;
        });
    }
    getTasks = function(date){
        var component = this;
        component.currentDate = date;
        this.restfulService.listCalendarTasks(date, function(res){
            component.users = res;
        });
    }
    formatTasks = function(projects){
        var task = "";
        for(var i=0; i<projects.length; i++){
            if(projects[i].project){
                if(i>0){
                    task+=",";
                }
                task+=projects[i].project+"["+projects[i].task+"] "+projects[i].hours+"h";
            }
        }
        return task;
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

