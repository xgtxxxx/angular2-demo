import './users.component.scss';
import { Component, Inject } from '@angular/core';
import { AppService } from '../common/app.service'
import { User } from '../model/User'
@Component({
    selector: 'app-users',
    templateUrl: './users.component.html'
})
export class UsersComponent {
    constructor (@Inject(AppService) private appService : AppService) {}
    users = [];
    error = '';
    isLoading = false;
    
}
