import {Injectable } from '@angular/core';
import { Http, Headers, RequestMethod } from '@angular/http';

@Injectable()
export class HttpClient {
    constructor (private http: Http) {}

    createAuthorizationHeader(headers:Headers) {
        headers.append('ODOO-API-TOKEN', '1234567890');
        // headers.append("X-Requested-With","XMLHttpRequest");
    }

    get(url) {
        let headers = new Headers();
        this.createAuthorizationHeader(headers);
        return this.http.get(url, {
            headers: headers,
            method : RequestMethod.Get
        });
    }

    post(url, data) {
        let headers = new Headers();
        this.createAuthorizationHeader(headers);
        headers.append("Content-Type","application/json");
        return this.http.post(url, data, {
            headers: headers,
            method : RequestMethod.Post
        });
    }
}