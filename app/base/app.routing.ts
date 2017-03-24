import AppConstants from './app.constants';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { UsersComponent } from '../users/users.component';
import {UsersAddFormComponent} from "../users/users.add-form.component";
import {UsersEditFormComponent} from "../users/users.edit-form.component";
import {AppErrorComponent} from "../error/app.error.component";
import {AppOperateComponent} from "../operate/app.operate.component";

const appRoutes:Routes = [
    {
        path: AppConstants.ROUTER_LOGIN,
        component: LoginComponent
    },
    {
        path: AppConstants.ROUTER_USERS,
        component: UsersComponent
    },
    {
        path: AppConstants.ROUTER_OPERATE,
        component: AppOperateComponent
    },
    {
        path: AppConstants.ROUTER_NEW_USER,
        component: UsersAddFormComponent
    },
    {
        path: AppConstants.ROUTER_EDIT_USER + "/:id",
        component: UsersEditFormComponent
    },
    {
        path: AppConstants.ROUTER_ERROR + "/:message",
        component: AppErrorComponent
    }
];

export const Router = RouterModule.forRoot(appRoutes);
