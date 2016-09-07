"use strict";
var router_1 = require('@angular/router');
var view_users_1 = require('./module/view.users');
var view_epic_links_1 = require('./module/view.epic.links');
var view_vacation_date_1 = require('./module/view.vacation.date');
var view_calendar_1 = require('./module/view.calendar');
var view_scrumblr_account_1 = require('./module/view.scrumblr.account');
var view_login_1 = require('./module/view.login');
var appRoutes = [
    {
        path: '',
        component: view_login_1.LoginComponent
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
    },
    {
        path: 'account',
        component: view_scrumblr_account_1.ScrumblrAccountComponent
    }
];
exports.routing = router_1.RouterModule.forRoot(appRoutes);
//# sourceMappingURL=app.routing.js.map