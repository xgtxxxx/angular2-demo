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
    templateUrl: './users.add-form.component.html'
})
export class UsersAddFormComponent {
    private newUser: User;
    private submitted : boolean;
    constructor (
        @Inject(AppService) private appService : AppService,
        @Inject(Router) private router : Router) {
        this.resetUser();
    }

    public saveUser(isValid){
        this.submitted = true;
        if(isValid){
            //TODO: do save
            alert('save')
        }
    }
    public clearForm(){
        this.submitted = false;
        this.resetUser();
    }
    public addTask(){
        this.newUser.timesheetTasks.push(new TimeSheetTask());
    }
    public deleteTask(index){
        this.newUser.timesheetTasks.splice(index, 1);
    }
    public back(){
        this.router.navigate(['/'+AppConstants.ROUTER_USERS])
    }
    private resetUser(){
        this.newUser = new User();
        this.newUser.active = true;
        this.newUser.account = new Account();
        this.newUser.timesheetTasks = [new TimeSheetTask()];
    }
}
