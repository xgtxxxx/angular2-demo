"use strict";
var router_1 = require('@angular/router');
var view_users_1 = require('./module/view.users');
var view_epic_links_1 = require('./module/view.epic.links');
var view_vacation_date_1 = require('./module/view.vacation.date');
var view_calendar_1 = require('./module/view.calendar');
var appRoutes = [
    {
        path: '',
        redirectTo: 'calendar',
        pathMatch: 'full'
    },
    {
        path: 'users',
        component: view_users_1.UsersComponent
    },
    {
        path: 'epic-links',
        component: view_epic_links_1.EpicLinksComponent
    },
    {
        path: 'vacation-dates',
        component: view_vacation_date_1.VacationDateComponent
    },
    {
        path: 'calendar',
        component: view_calendar_1.CalendarComponent
    }
];
exports.routing = router_1.RouterModule.forRoot(appRoutes);
//# sourceMappingURL=app.routing.js.map