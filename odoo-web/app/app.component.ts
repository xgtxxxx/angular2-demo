import { Component } from '@angular/core';
@Component({
    selector: 'my-app',
    templateUrl:"/views/home.html"
})

export class AppComponent{
    public vacationDates = "/vacation-dates";
    public epicLinks = "/epic-links";
    public users = "/users";
    public calendar = "/calendar";
    routers = [
        {href: this.calendar, name:"calendar"},
        {href: this.users ,name:"users"},
        {href: this.epicLinks, name:"epic links"},
        {href: this.vacationDates, name:"vacation dates"}
    ];
    setActive = function(router){
        for(var i=0; i<this.routers.length; i++){
            if(this.routers[i].active){
                this.routers[i].active=false;
                break;
            }
        }
        router.active=true;
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
}

