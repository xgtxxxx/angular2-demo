import { Component, OnInit } from '@angular/core';
import { RestfulService } from '../service/restful.service';
import { Response } from '@angular/http';
import {AppComponent} from '../app.component';
@Component({
    selector: 'app-users',
    templateUrl:"views/users.html"
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
    tasks = ["Build","Testing","Plan"];
    ngOnInit(){
        this.parent.setActiveByPath(this.parent.users);
        this.refresh();
        this.getEpicLinks();
    };
    refresh = function(){
        var component = this;
        this.restfulService.listUser().subscribe(function (res:Response) {
                console.log(res.json());
                component.users = res.json();
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
    doSave = function(){
        var component = this;
        this.restfulService.saveUser(this.editUser).subscribe(function (res:Response) {
                if(res.json().key=='success'){
                    $("#newAddModal").modal("hide");
                    component.refresh();
                }
            });
    }

    getEpicLinks = function(){
        var component = this;
        this.restfulService.listEpicLinks().subscribe(function (res:Response) {
                console.log(res.json());
                component.epicLinks = res.json();
            });
    }

    addCustom = function(user){
        user.customs.projects.push({});
    }
}

