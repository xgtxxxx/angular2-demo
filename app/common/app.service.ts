import {Injectable, Inject} from '@angular/core';
import {Http} from "@angular/http";

@Injectable()
export class AppService {
    constructor(@Inject(Http) private http : Http) {
    }

    public saveUser(user) {
        return this.http.post("odoo/admin/users", user);
    }

    public getUser(id){
        return this.http.get("odoo/admin/users/" + id );
    }

    public getCurrentUser() {
        return this.http.get("odoo/admin/current-user");
    }

    public getUsers() {
        return this.http.get("odoo/admin/users");
    }

    public login(user){
        return this.http.post("odoo/admin/login", user);
    }

    public logTime(date, ids){
        return this.http.post("odoo/admin/log-time", {
            date : date,
            userIds : ids
        })
    }
}