import { NgModule } from '@angular/core';
import { HttpModule }    from '@angular/http';
import { FormsModule, NgForm }    from '@angular/forms';
import { BrowserModule }  from '@angular/platform-browser';
import { LocationStrategy, HashLocationStrategy } from "@angular/common";
import { AppService } from '../common/app.service'
import { Router }        from './app.routing';
import { AppComponent } from './app.component';
import { LoginComponent } from "../login/login.component";
import { UsersComponent } from "../users/users.component";
import { LoadingButton } from "../common/app.common.loading-button";
import { UsersAddFormComponent } from "../users/users.add-form.component";
import { UsersEditFormComponent } from "../users/users.edit-form.component";
import {AppErrorComponent} from "../error/app.error.component";
import {AppOperateComponent} from "../operate/app.operate.component";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        Router
    ],
    declarations: [
        AppComponent,
        LoginComponent,
        UsersComponent,
        LoadingButton,
        UsersAddFormComponent,
        UsersEditFormComponent,
        AppErrorComponent,
        AppOperateComponent
    ],
    providers: [
        AppService,
        {provide: LocationStrategy, useClass: HashLocationStrategy}
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
