import { Component, OnInit, Inject } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import AppConstants from './app.constants'
import { User } from '../model/User'
import {AppService} from "../common/app.service";

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
    constructor(
        @Inject(Router) private router : Router,
        @Inject(ActivatedRoute) private activatedRoute : ActivatedRoute,
        @Inject(AppService) private appService : AppService) { }
    private user = new User();
    private routers = [
        AppConstants.ROUTER_USERS
    ]
    ngOnInit():void {
        let vm = this;
        this.appService.getUser().subscribe(
            res => {
                vm.user = res.json() as User;
                if(!vm.activatedRoute){
                    vm.router.navigate(['/'+AppConstants.ROUTER_USERS]);
                }
            },
            err => {
                vm.router.navigate(['/'+AppConstants.ROUTER_LOGIN])
            }
        )
    }
    public getUser = function(){
        return this.user;
    }
    public setUser = function(user){
        this.user = user;
    }
}
