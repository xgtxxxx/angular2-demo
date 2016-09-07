import {Injectable } from '@angular/core';
import { Http, Headers, RequestMethod, Response } from '@angular/http';

@Injectable()
export class HttpClient {
    constructor (private http: Http) {}

    createAuthorizationHeader(headers:Headers, isLogin) {
        headers.append('ODOO-API-TOKEN', '1234567890');
        if(!isLogin){
            headers.append('x-auth-token',this.getToken());
        }
    }

    toServerError(){
        window.location.href="/error.html";
    }

    get(url, callback) {
        var clinet = this;
        let headers = new Headers();
        this.createAuthorizationHeader(headers, false);
        var result = this.http.get(url, {
            headers: headers,
            method : RequestMethod.Get
        });
        this.processResult(result,callback);
    }

    post(url, data, callback, isLogin) {
        let headers = new Headers();
        this.createAuthorizationHeader(headers, isLogin);
        headers.append("Content-Type","application/json");
        var result = this.http.post(url, data, {
                headers: headers,
                method : RequestMethod.Post
            });
        this.processResult(result,callback);
    }

    processResult(result, callback){
        var client = this;
        result.subscribe(function(res: Response){
            callback(res.json());
        },function(error: any){
            console.log(error)
            client.toServerError();
        });
    }

    getToken = function(){
        var token = sessionStorage.getItem("token");
        if(token){
            return token;
        }else{
            window.location.href="/error.html";
            return;
        }
    }
}