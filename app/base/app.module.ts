import { NgModule } from '@angular/core';
import { HttpModule }    from '@angular/http';
import { FormsModule }    from '@angular/forms';
import { BrowserModule }  from '@angular/platform-browser';
import { AppService } from '../common/app.service'
import { Router }        from './app.routing';
import { AppComponent } from './app.component';
import {LoginComponent} from "../login/login.component";
import { UsersComponent } from "../users/users.component";
import { LoadingButton } from "../common/app.common.loading-button";
import {LocationStrategy, HashLocationStrategy} from "@angular/common";

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
        LoadingButton
    ],
    providers: [
        AppService,
        {provide: LocationStrategy, useClass: HashLocationStrategy}
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
