import {Injectable } from '@angular/core';
import { HttpClient } from './http.client';
import {MockData} from '../mock/mock.data';

@Injectable()
export class RestfulService {
    constructor (private httpClient: HttpClient, private mockData: MockData) {}

    listUser = function(callback){
        if(isDemo){
            return this.mockData.listUser(callback);
        }else{
            return this.httpClient.get(getHost()+"/admin/user/list.do", callback);
        }
    }
    saveUser = function(user,callback){
        if(isDemo){
            return this.mockData.saveUser(user, callback);
        }else{
            return this.httpClient.post(getHost()+"/admin/user/save.do",JSON.stringify(user),callback);
        }
    }
    listEpicLinks = function(callback){
        if(isDemo){
            return this.mockData.listEpicLinks(callback);
        }else{
            return this.httpClient.get(getHost()+"/admin/epicLink/list.do",callback);
        }
    }
    saveEpicLink = function(epicLink,callback){
        if(isDemo){
            return this.mockData.saveEpicLink(epicLink, callback);
        }else{
            return this.httpClient.post(getHost()+"/admin/epicLink/save.do",JSON.stringify(epicLink),callback);
        }
    }
    listVacationDates = function(year,callback){
        if(isDemo){
            return this.mockData.listVacationDates(year, callback);
        }else{
            return this.httpClient.get(getHost()+"/admin/vacationDate/list/"+year+".do",callback);
        }
    }
    saveVacationDate = function(vacationDate,callback){
        if(isDemo){
            return this.mockData.saveVacationDate(vacationDate, callback);
        }else{
            return this.httpClient.post(getHost()+"/admin/vacationDate/save.do", JSON.stringify(vacationDate),callback);
        }
    }
    deleteVacationDate = function(date,callback){
        if(isDemo){
            return this.mockData.deleteVacationDate(date, callback);
        }else{
            return this.httpClient.post(getHost()+"/admin/vacationDate/delete.do", JSON.stringify(date),callback);
        }
    }
    listCalendar = function(year,month,callback){
        if(isDemo){
            return this.mockData.listCalendar(year, month, callback);
        }else{
            return this.httpClient.get(getHost()+"/admin/calendar/list/"+year+"/"+month+".do",callback);
        }
    }
    listCalendarTasks = function(date,callback){
        if(isDemo){
            return this.mockData.listCalendarTasks(date, callback);
        }else{
            return this.httpClient.get(getHost()+"/admin/calendar/tasks/"+date+".do",callback);
        }
    }
    saveCalendarTasks = function(date, users, callback){
        if(isDemo){
            return this.mockData.saveCalendarTasks(date, users, callback);
        }else{
            return this.httpClient.post(getHost()+"/admin/calendar/tasks/save/"+date+".do",JSON.stringify(users), callback);
        }
    }
    listScrumblrUser = function(callback){
        if(isDemo){
            return this.mockData.listScrumblrUser(callback);
        }else{
            return this.httpClient.get(getHost()+"/admin/scrumblr/users.do", callback);
        }
    }
    saveScrumblrUser = function(user, callback){
        if(isDemo){
            return this.mockData.saveScrumblrUser(user, callback);
        }else{
            return this.httpClient.post(getHost()+"/admin/scrumblr/user/save.do",JSON.stringify(user), callback);
        }
    }
    deleteScrumblrUser = function(user, callback){
        if(isDemo){
            return this.mockData.deleteScrumblrUser(user, callback);
        }else{
            return this.httpClient.post(getHost()+"/admin/scrumblr/user/delete.do",JSON.stringify(user), callback);
        }
    }
    login = function(account, callback){
        if(isDemo){
            return this.mockData.login(account, callback);
        }else{
            return this.httpClient.post(getHostUrl(account.env)+"/admin/user/login.do",JSON.stringify(account), callback, true);
        }
    }
}

var isDemo = true;

function getHostUrl(e){
    var env = e?e:sessionStorage.getItem("env");
    if(env && env=='prod'){
        return HOST_PROD;
    }else{
        return HOST_DEV;
    }
}

function getHost(){
    return getHostUrl(null);
}

var HOST_DEV = "http://172.17.50.8:8088/demo";
var HOST_PROD = "http://172.17.50.8:8088/odoo";