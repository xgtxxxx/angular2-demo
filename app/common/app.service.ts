import {Injectable, Inject} from '@angular/core';
import {Http} from "@angular/http";

@Injectable()
export class AppService {
    private demo;
    constructor(@Inject(Http) private http : Http) {
    }

    public saveUser(user) {
        return this.http.post("odoo/admin/users", user);
    }

    public getUser(id){
        if(this.isDemo()){
            return this.http.get("/app/data/mock-user.json");
        }else{
            return this.http.get("odoo/admin/users/" + id );
        }
    }

    public getCurrentUser() {
        return this.http.get("odoo/admin/current-user");
    }

    public getUsers() {
        if(this.isDemo()){
            return this.http.get("/app/data/mock-users.json");
        }else{
            return this.http.get("odoo/admin/users");
        }
    }

    public login(user, demo){
        this.demo = demo;
        if(this.isDemo()){
            return this.http.get("/app/data/mock-login.json");
        }else{
            return this.http.post("odoo/admin/login", user);
        }
    }

    public logTime(date, ids){
        return this.http.post("odoo/admin/log-time", {
            date : date,
            userIds : ids
        })
    }

    private isDemo(){
        return this.demo;
    }
}