import './users.component.scss';
import { Component, Inject } from '@angular/core';
import { Router, ActivatedRoute, Params } from "@angular/router";
import { AppService } from '../common/app.service'
import { User } from '../model/User'
import { Account } from "../model/Account";
import { TimeSheetTask } from "../model/TimeSheetTask";
import AppConstants from "../base/app.constants";
@Component({
    selector: 'app-users',
    templateUrl: './users.edit-form.component.html'
})
export class UsersEditFormComponent {
    private error : string;
    private editUser : User;
    private submitted : boolean;
    private passwordUpdated : boolean;
    private newPassword : string;
    constructor (
        @Inject(AppService) private appService : AppService,
        @Inject(Router) private router : Router,
        @Inject(ActivatedRoute) private activatedRoute : ActivatedRoute) {

        this.resetUser();
    }
    private resetUser(){
        let vm = this;
        vm.error = "";
        vm.editUser = new User();
        vm.editUser.account = new Account();
        vm.activatedRoute.params.subscribe(params => {
            vm.appService.getUser(params['id']).subscribe(
                res => {
                    vm.editUser = res.json();
                },
                err => {
                    vm.router.navigate(['/'+AppConstants.ROUTER_ERROR, err.json().toString()])
                }
            )
        });

    }
    public saveUser(isValid) {
        let vm = this;
        vm.submitted = true;
        vm.error = "";
        if(isValid){
            if(vm.editUser.timesheetTasks.length==0){
                vm.error = "Time sheet tasks should not be empty!"
            }else{
                vm.appService.saveUser(vm.editUser).subscribe(
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
    public updatePassword(){
        this.passwordUpdated = true;
        if(this.newPassword){
            this.editUser.account.password = this.newPassword;
            this.saveUser(true);
        }
    }
    public addTask(){
        this.editUser.timesheetTasks.push(new TimeSheetTask());
    }
    public deleteTask(index){
        this.editUser.timesheetTasks.splice(index, 1);
    }
    public resetForm(){
        this.submitted = false;
        this.resetUser();
    }
    public back(){
        this.router.navigate(['/'+AppConstants.ROUTER_USERS])
    }

}
