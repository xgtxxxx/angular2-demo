import { Component, OnInit } from '@angular/core';
import {Router}  from '@angular/router';
import { RestfulService } from '../service/restful.service';
import { Response } from '@angular/http';
import {AppComponent} from '../app.component';
@Component({
    selector: 'app-login',
    templateUrl:'../views/login.html'
})

export class LoginComponent implements OnInit{
    constructor (private restfulService: RestfulService, private parent: AppComponent, private _router: Router) {}
    ngOnInit(){
        $("#newAddModal").modal("show");
        this.login();
    };
    login = function(){
        var name = this.parent.loginUser.uname;
        var env = this.parent.loginUser.env;
        if(!name){
            var token = sessionStorage.getItem("token");
            if(token){
                $("#newAddModal").modal("hide");
                this._router.navigate( ['calendar', {}] )
            }else{
                window.location.href="/access.html";
            }
        }else{
            sessionStorage.clear();
            var component = this;
            console.log(this.parent.loginUser)
            this.restfulService.login(this.parent.loginUser, function(res){
                var result = res;
                if(result.success){
                    $("#newAddModal").modal("hide");
                    sessionStorage.setItem("token",result.token);
                    sessionStorage.setItem("name",name);
                    sessionStorage.setItem("env",env);
                    component._router.navigate( ['calendar', {}] )
                }else{
                    window.location.href="/access.html";
                }
            });
        }
    }
}

