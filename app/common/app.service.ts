import {Injectable, Inject} from '@angular/core';
import {Http} from "@angular/http";

@Injectable()
export class AppService {
    constructor(@Inject(Http) private http : Http) {
    }

    public getUser(){
        return this.http.get("odoo/admin/current-user");
    }

    public login(user){
        return this.http.post("odoo/admin/login", user);
    }
}