"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var http_1 = require('@angular/http');
var HttpClient = (function () {
    function HttpClient(http) {
        this.http = http;
        this.getToken = function () {
            var token = sessionStorage.getItem("token");
            if (token) {
                return token;
            }
            else {
                window.location.href = "/error.html";
                return;
            }
        };
    }
    HttpClient.prototype.createAuthorizationHeader = function (headers, isLogin) {
        headers.append('ODOO-API-TOKEN', '1234567890');
        if (!isLogin) {
            headers.append('x-auth-token', this.getToken());
        }
    };
    HttpClient.prototype.toServerError = function () {
        window.location.href = "/error.html";
    };
    HttpClient.prototype.get = function (url, callback) {
        var clinet = this;
        var headers = new http_1.Headers();
        this.createAuthorizationHeader(headers, false);
        var result = this.http.get(url, {
            headers: headers,
            method: http_1.RequestMethod.Get
        });
        this.processResult(result, callback);
    };
    HttpClient.prototype.post = function (url, data, callback, isLogin) {
        var headers = new http_1.Headers();
        this.createAuthorizationHeader(headers, isLogin);
        headers.append("Content-Type", "application/json");
        var result = this.http.post(url, data, {
            headers: headers,
            method: http_1.RequestMethod.Post
        });
        this.processResult(result, callback);
    };
    HttpClient.prototype.processResult = function (result, callback) {
        var client = this;
        result.subscribe(function (res) {
            callback(res.json());
        }, function (error) {
            console.log(error);
            client.toServerError();
        });
    };
    HttpClient = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], HttpClient);
    return HttpClient;
}());
exports.HttpClient = HttpClient;
//# sourceMappingURL=http.client.js.map