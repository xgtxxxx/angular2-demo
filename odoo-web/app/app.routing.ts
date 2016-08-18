import { Routes, RouterModule } from '@angular/router';

import { UsersComponent } from './module/view.users';
import { EpicLinksComponent } from './module/view.epic.links';
import { VacationDateComponent } from './module/view.vacation.date';
import { CalendarComponent } from './module/view.calendar';

const appRoutes: Routes = [
    {
        path: '',
        redirectTo : 'calendar',
        pathMatch: 'full'
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
    }
];

export const routing = RouterModule.forRoot(appRoutes);
