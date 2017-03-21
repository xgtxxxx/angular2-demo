import './users.component.scss';
import { Component, Inject } from '@angular/core';
import { AppService } from '../common/app.service'
import { User } from '../model/User'
import {Account} from "../model/Account";
import {TimeSheetTask} from "../model/TimeSheetTask";
import {Router, ActivatedRoute, Params} from "@angular/router";
import AppConstants from "../base/app.constants";
@Component({
    selector: 'app-users',
    templateUrl: './users.edit-form.component.html'
})
export class UsersEditFormComponent {
    private editUser : User;
    private submitted : boolean;
    private passwordUpdated : boolean;
    private newPassword : string;
    constructor (
        @Inject(AppService) private appService : AppService,
        @Inject(Router) private router : Router,
        @Inject(ActivatedRoute) private activatedRoute : ActivatedRoute) {
        this.activatedRoute.params
            .switchMap((params: Params) => params['id'])
            .subscribe(userId => console.log(userId));
        this.resetUser();
    }

    public saveUser(isValid){
        this.submitted = true;
        if(isValid){
            //TODO: do save
            alert('save')
        }
    }
    public resetForm(){
        this.submitted = false;
        this.resetUser();
    }
    public addTask(){
        this.editUser.timesheetTasks.push(new TimeSheetTask());
    }
    public deleteTask(index){
        this.editUser.timesheetTasks.splice(index, 1);
    }
    public updatePassword(){
        this.passwordUpdated = true;
        if(this.newPassword){
            //TODO: update password
        }
    }
    public back(){
        this.router.navigate(['/'+AppConstants.ROUTER_USERS])
    }
    private resetUser(){
        //TODO: get user from backend.
        let user1 = new User();
        user1.id = "1";
        user1.name = "Gavin";
        user1.active = true;
        user1.account = new Account();
        user1.account.login = "gxi";
        user1.mailAddress = "demo@demo.com";
        user1.authority = "all";
        let task = new TimeSheetTask();
        task.hours = 8;
        task.project = "Project Name";
        task.task = "Build";
        user1.timesheetTasks = [task];

        this.editUser = user1;

        // this.activatedRoute.params
        //     .switchMap((params: Params) => AppService.getUser(params['id']))
        //     .subscribe(user => this.editUser = user);
    }
}
