import { Component, OnInit } from '@angular/core';
import { RestfulService } from '../service/restful.service';
import { Response } from '@angular/http';
import {AppComponent} from '../app.component';
@Component({
    selector: 'app-users',
    templateUrl:"../views/users.html"
})

export class UsersComponent implements OnInit{
    constructor (private restfulService: RestfulService, private parent: AppComponent) {}
    users = [];
    epicLinks = [];
    editUser = {
        customs : {
            projects : [{}]
        }
    };
    ngOnInit(){
        this.parent.setActiveByPath(this.parent.users);
        this.refresh();
        this.getEpicLinks();
    };
    refresh = function(){
        var component = this;
        this.restfulService.listUser(function(res){
            component.users = res;
            for(var i=0; i<component.users.length; i++){
                var user = component.users[i];
                if(!user.customs){
                    user.customs = {
                        active : false,
                        projects : [
                            {}
                        ]
                    }
                }
            }
        });
    }
    newUser = function(){
        this.editUser = {
            customs : {
                active : false,
                projects : [{}]
            }
        };
        $("#newAddModal").modal("show");
    }
    edit = function(user){
        this.editUser = JSON.parse(JSON.stringify(user));
        $("#newAddModal").modal("show");
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
    doSave = function(){
        var component = this;
        this.restfulService.saveUser(this.editUser, function(res){
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

    addCustom = function(user){
        user.customs.projects.push({});
    }
}

