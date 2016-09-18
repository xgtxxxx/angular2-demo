import { Routes, RouterModule } from '@angular/router';

import { UsersComponent } from './module/view.users';
import { EpicLinksComponent } from './module/view.epic.links';
import { VacationDateComponent } from './module/view.vacation.date';
import { CalendarComponent } from './module/view.calendar';
import { ScrumblrAccountComponent } from './module/view.scrumblr.account';
import { LoginComponent } from './module/view.login';

const appRoutes: Routes = [
    {
        path: '',
        component: LoginComponent
    },
    {
        path: 'users',
        component: UsersComponent
    },
    {
        path: 'epic-links',
        component: EpicLinksComponent
    },
    {
        path: 'vacation-dates',
        component: VacationDateComponent
    },
    {
        path: 'calendar',
        component: CalendarComponent
    },
    {
        path: 'account',
        component: ScrumblrAccountComponent
    }
];

export const routing = RouterModule.forRoot(appRoutes);
