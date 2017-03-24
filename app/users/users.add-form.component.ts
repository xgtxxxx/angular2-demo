import './users.component.scss';
import { Component, Inject, forwardRef } from '@angular/core';
import { AppService } from '../common/app.service'
import { User } from '../model/User'
import { Account } from "../model/Account";
import { TimeSheetTask } from "../model/TimeSheetTask";
import { Router } from "@angular/router";
import {AppComponent} from "../base/app.component";
import AppConstants from "../base/app.constants";
@Component({
    selector: 'app-users',
    templateUrl: './users.add-form.component.html'
})
export class UsersAddFormComponent {
    private error: string;
    private newUser: User;
    private submitted : boolean;
    constructor (
        @Inject(AppService) private appService : AppService,
        @Inject(Router) private router : Router,
        @Inject(forwardRef(()=> AppComponent)) private parent: AppComponent) {
        //TODO: watch router change is a better way to check the authority.
        if(parent.checkAuthority()){
            this.resetUser();
        }else {
            this.router.navigate(['/'+AppConstants.ROUTER_ERROR, 'Not found!'])
        }
    }
    private resetUser(){
        this.newUser = new User();
        this.newUser.active = true;
        this.newUser.account = new Account();
        this.newUser.timesheetTasks = [new TimeSheetTask()];
    }
    public saveUser(isValid){
        let vm = this;
        vm.submitted = true;
        vm.error = "";
        if(isValid){
            if(vm.newUser.timesheetTasks.length==0){
                vm.error = "Time sheet tasks should not be empty!"
            }else{
                vm.appService.saveUser(vm.newUser).subscribe(
                    res => {
                        vm.router.navigate(['/'+AppConstants.ROUTER_USERS])
                    },
                    err => {
                        vm.error = err.message;
                    }
                )
            }
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
}
