import {Injectable } from '@angular/core';
import { HttpClient } from '../service/http.client';

@Injectable()
export class RestfulService {
    constructor (private httpClient: HttpClient) {}

    listUser = function(){
        return this.httpClient.get(HOST+"/odoo/admin/user/list.do");
    }
    saveUser = function(user){
        return this.httpClient.post(HOST+"/odoo/admin/user/save.do",JSON.stringify(user));
    }
    listEpicLinks = function(){
        return this.httpClient.get(HOST+"/odoo/admin/epicLink/list.do");
    }
    saveEpicLink = function(epicLink){
        return this.httpClient.post(HOST+"/odoo/admin/epicLink/save.do",JSON.stringify(epicLink));
    }
    listVacationDates = function(year){
        return this.httpClient.get(HOST+"/odoo/admin/vacationDate/list/"+year+".do");
    }
    saveVacationDate = function(vacationDate){
        return this.httpClient.post(HOST+"/odoo/admin/vacationDate/save.do", JSON.stringify(vacationDate));
    }
    deleteVacationDate = function(date){
        return this.httpClient.post(HOST+"/odoo/admin/vacationDate/delete.do", JSON.stringify(date));
    }
    listCalendar = function(year,month){
        return this.httpClient.get(HOST+"/odoo/admin/calendar/list/"+year+"/"+month+".do");
    }
}

// var HOST = "http://172.17.50.8:8088";
var HOST = "http://localhost:8080";