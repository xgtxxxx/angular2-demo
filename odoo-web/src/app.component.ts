import { Component, OnInit } from '@angular/core';
@Component({
    selector: 'my-app',
    templateUrl:"./views/home.html"
})

export class AppComponent implements OnInit{
    public vacationDates = "/vacation-dates";
    public epicLinks = "/epic-links";
    public users = "/users";
    public calendar = "/calendar";
    public account = "/account";
    public loginUser = {};
    hello = "";
    routers = [
        {href: this.calendar, name:"calendar"},
        {href: this.users ,name:"users"},
        {href: this.epicLinks, name:"epic links"},
        {href: this.vacationDates, name:"vacation dates"},
        {href: this.account, name:"Scrumblr account"}
    ];
    ngOnInit(){
        var name = this.getQueryString("account");
        var password = this.getQueryString("pwd");
        var env = this.getQueryString("env");
        this.loginUser = {
            uname : name,
            pwd : password,
            env : env
        }
        if(name){
            this.hello = name;
        }else{
            this.hello = sessionStorage.getItem("name");
        }
    };
    setActiveByPath = function(path){
        for(var i=0; i<this.routers.length; i++){
            if(this.routers[i].active){
                this.routers[i].active=false;
                break;
            }
        }
        for(var i=0;i<this.routers.length; i++){
            var router = this.routers[i];
            if(router.href==path){
                router.active=true;
            }
        }
    }
    getQueryString = function(name){
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)return  r[2]; return null;
    }
}

