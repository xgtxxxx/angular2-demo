import { Component, OnInit } from '@angular/core';
import { RestfulService } from '../service/restful.service';
import { Response } from '@angular/http';
import {AppComponent} from '../app.component';
@Component({
    selector: 'app-scrumblr-account',
    templateUrl:"../views/scrumblr-account.html"
})

export class ScrumblrAccountComponent implements OnInit{
    constructor (private restfulService: RestfulService, private parent: AppComponent) {}
    users = [];
    editUser = {
        points: 0
    };
    authorities = [{id:1,name:'Member'},{id:9, name:'Admin'}];
    isEdit = undefined;
    ngOnInit(){
        this.parent.setActiveByPath(this.parent.account);
        this.refresh();
    };
    refresh = function(){
        var component = this;
        this.restfulService.listScrumblrUser(function(res){
            component.users = res;
        });
    }
    newUser = function(){
        this.editUser = {points: 0};
        this.isEdit = undefined;
        $("#newAddModal").modal("show");
    }
    edit = function(user){
        this.editUser = JSON.parse(JSON.stringify(user));
        this.isEdit = true;
        $("#newAddModal").modal("show");
    }
    doSave = function(){
        var component = this;
        this.restfulService.saveScrumblrUser(this.editUser, function(res){
            if(res.key=='success'){
                $("#newAddModal").modal("hide");
                component.refresh();
            }
        });
    }
    del = function(user){
        if(confirm("Sure to delete it?")){
            var component = this;
            this.restfulService.deleteScrumblrUser(user, function(res){
                if(res.key=='success'){
                    component.refresh();
                }
            });
        }
    }
}

