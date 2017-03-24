import './app.operate.component.scss';
import {Component, Inject, forwardRef} from '@angular/core';
import { AppService } from '../common/app.service';
import { User } from '../model/User';
import {AppComponent} from "../base/app.component";
import { Router } from "@angular/router";
import AppConstants from "../base/app.constants";
import {Account} from "../model/Account";
import moment from "moment";
@Component({
    selector: 'app-operate',
    templateUrl: './app.operate.component.html'
})
export class AppOperateComponent{
    private isLoading = false;
    private users : User[];
    private date : string;
    private error : string;
    private message : string;
    constructor (
        @Inject(AppService) private appService : AppService,
        @Inject(Router) private router : Router,
        @Inject(forwardRef(()=> AppComponent)) private parent: AppComponent) {

        if(this.parent.checkAuthority()){
            this.init();
        }else{
            this.router.navigate(['/'+AppConstants.ROUTER_ERROR, 'Not found!'])
        }
    }

    public selectAll(){
        for(let i in this.users){
            this.users[i].selected = true;
        }
    }

    public clear(){
        this.error = "";
        this.message = "";
        for(let i in this.users){
            this.users[i].selected = false;
        }
    }

    public log(){
        let vm = this;
        let selectedUsers = [];
        for(let i in vm.users){
            let user = vm.users[i];
            if(user.selected){
                selectedUsers.push(user);
            }
        }
        if(vm.validate(selectedUsers)){
            if(confirm("Confirm?")==true){
                vm.doLog(vm.date, selectedUsers);
            }
        }
    }

    private doLog(date, selectedUsers){
        let vm = this;
        vm.isLoading = true;
        let ids = [];
        for(let i in selectedUsers){
            ids.push(selectedUsers[i].id);
        }
        this.appService.logTime(date, ids).subscribe(
            res => {
                vm.message = "Log time success!"
                vm.isLoading = false;
            },
            err => {
                vm.error = "Log time error!";
                vm.isLoading = false;
            });
    }

    private validate(selectedUsers){
        let vm = this;
        let flag = true;
        if(!this.date){
            flag = false;
            vm.error = "Date error!"
        }
        if(selectedUsers.length<=0){
            flag = false;
            vm.error = "No user selected!";
        }

        return flag;
    }

    private init(){
        let vm = this;
        vm.isLoading = true;
        vm.date = moment().format('YYYY-MM-DD');
        vm.appService.getUsers().subscribe(
            res => {
                vm.users = res.json().sort((u1, u2)=>u1.name.localeCompare(u2.name));
                vm.isLoading = false;
            },
            err => {
                vm.error = "Can not find users!";
                vm.isLoading = false;
            }
        )
    }
}
