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
    private loading = false;
    private routers = [
        {
            link : AppConstants.ROUTER_USERS,
            authority : ''
        },{
            link :  AppConstants.ROUTER_OPERATE,
            authority : 'all'
        }
    ]
    ngOnInit():void {
        this.loading = true;
        let vm = this;
        this.appService.getCurrentUser().subscribe(
            res => {
                vm.user = res.json() as User;
                vm.loading = false;
                if(!vm.activatedRoute){
                    vm.router.navigate(['/'+AppConstants.ROUTER_USERS]);
                }
            },
            err => {
                vm.loading = false;
                vm.router.navigate(['/'+AppConstants.ROUTER_LOGIN]);
            }
        )
    }
    public getUser = function(){
        return this.user;
    }
    public setUser = function(user){
        this.user = user;
    }
    public checkAuthority(){
        return this.loading ? 'all' : this.user.authority == 'all';
    }
}
