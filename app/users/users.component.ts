import './users.component.scss';
import { Component, Inject } from '@angular/core';
import { AppService } from '../common/app.service'
import { User } from '../model/User'
import {Account} from "../model/Account";
import {TimeSheetTask} from "../model/TimeSheetTask";
import {Router} from "@angular/router";
import AppConstants from "../base/app.constants";
@Component({
    selector: 'app-users',
    templateUrl: './users.component.html'
})
export class UsersComponent {
    private users;
    constructor (
        @Inject(AppService) private appService : AppService,
        @Inject(Router) private router : Router) {
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

        let user2 = new User();
        user2.id = "2";
        user2.name = "Clark";
        user2.active = false;
        user2.account = new Account();
        user2.account.login = "cchen";
        user2.mailAddress = "clark@demo.com";
        user2.authority = "clark";
        let task1 = new TimeSheetTask();
        task1.hours = 8;
        task1.project = "Project Name";
        task1.task = "Build";
        user2.timesheetTasks = [task1];
        this.users = [user1, user2];
    }

    public newUser(){
        this.router.navigate(['/'+AppConstants.ROUTER_ADD_USER])
    }

    public editUser(user: User){
        this.router.navigate(['/'+AppConstants.ROUTER_EDIT_USER, user.id])
    }
}
