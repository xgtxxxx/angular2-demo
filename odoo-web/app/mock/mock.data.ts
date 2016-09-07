import {Injectable} from '@angular/core';

@Injectable()
export class MockData {
    listUser = function (callback) {
        callback(Users);
    }
    saveUser = function (user, callback) {
        var isUpdate = false;
        for(var i=0; i<Users.length; i++){
            if(user.name==Users[i].name){
                Users[i] = user;
                isUpdate = true;
            }
        }
        if(!isUpdate){
            Users.push(user);
        }
        var data = {"key": "success", "value": "true", "valueAsString": "\"true\""};
        callback(data);
    }
    listEpicLinks = function (callback) {
        callback(Epics);
    }
    saveEpicLink = function (epicLink, callback) {
        var isUpdate = false;
        for(var i=0; i<Epics.length; i++){
            if(epicLink.epicName==Epics[i].epicName){
                Epics[i] = epicLink;
                isUpdate = true;
            }
        }
        if(!isUpdate){
            Epics.push(epicLink);
        }
        var data = {"key": "success", "value": "true", "valueAsString": "\"true\""};
        callback(data);
    }
    listVacationDates = function (year, callback) {
        callback(VacationDate);
    }
    saveVacationDate = function (vacationDate, callback) {
        var isUpdate = false;
        for(var i=0; i<VacationDate.length; i++){
            if(vacationDate.date==VacationDate[i].date){
                VacationDate[i] = vacationDate;
                isUpdate = true;
            }
        }
        if(!isUpdate){
            VacationDate.push(vacationDate);
        }
        var data = {"key": "success", "value": "true", "valueAsString": "\"true\""};
        callback(data);
    }
    deleteVacationDate = function (date, callback) {
        var dates = [];
        for(var i=0; i<VacationDate.length; i++){
            if(date.date!=VacationDate[i].date){
                dates.push(VacationDate[i]);
            }
        }
        VacationDate = dates;
        var data = {"key": "success", "value": "true", "valueAsString": "\"true\""};
        callback(data);
    }
    listCalendar = function (year, month, callback) {
        callback(Calendar);
    }
    listCalendarTasks = function (date, callback) {
        callback(CalendarTasks);
    }
    saveCalendarTasks = function (date, users, callback) {
        CalendarTasks = users;
        var data = {"key": "success", "value": "true", "valueAsString": "\"true\""};
        callback(data);
    }
    listScrumblrUser = function (callback) {
        callback(SCRUMBLR_USER);
    }
    saveScrumblrUser = function (user, callback) {
        var isUpdate = false;
        for(var i=0; i<SCRUMBLR_USER.length; i++){
            if(user.uname==SCRUMBLR_USER[i].uname){
                SCRUMBLR_USER[i]=user;
                isUpdate = true;
            }
        }
        if(!isUpdate){
            SCRUMBLR_USER.push(user);
        }
        var data = {"key": "success", "value": "true", "valueAsString": "\"true\""};
        callback(data);
    }
    deleteScrumblrUser = function (user, callback) {
        var users = [];
        for(var i=0; i<SCRUMBLR_USER.length; i++){
            if(user.uname!=SCRUMBLR_USER[i].uname){
                users.push(SCRUMBLR_USER[i]);
            }
        }
        SCRUMBLR_USER = users;

        var data = {"key": "success", "value": "true", "valueAsString": "\"true\""};
        callback(data);
    }
    login = function (account, callback) {
        var data = {"success": true, "token": "58d7767d-57c1-4672-b6f6-9d4de06a1cde"};
        callback(data);
    }
}

var SCRUMBLR_USER = [{
        "uname": "User1",
        "pwd": "123",
        "mail": "user1@mock.com",
        "points": 750,
        "authority": 1
    },
    {
        "uname": "User2",
        "pwd": "123",
        "mail": "user2@mock.com",
        "points": 584,
        "authority": 1
    },
    {
        "uname": "User3",
        "pwd": "123",
        "mail": "user3@mock.com",
        "points": 6,
        "authority": 1
    },
    {
        "uname": "User4",
        "pwd": "123",
        "mail": "user4@mock.com",
        "points": 6,
        "authority": 1
    }];

var CalendarTasks = [{
    "name": "User1",
    "account": "user1",
    "password": "111111",
    "defaultTask": "Testing",
    "customs": {"active": false, "projects": [{"project": null, "task": null, "hours": 0.0}]},
    "date": "2016-09-7"
}, {
    "name": "User2",
    "account": "user2",
    "password": "111111",
    "defaultTask": "Build",
    "customs": {"active": true, "projects": [{"project": "TestProject1", "task": "Build", "hours": 8.0}]},
    "date": "2016-09-7"
}];

var Calendar = [[], [{
    "year": 0,
    "month": 0,
    "day": 0,
    "date": null,
    "weekDay": 0,
    "type": 0,
    "currentDate": false,
    "stringDate": "2016-09-07"
}, {
    "year": 0,
    "month": 0,
    "day": 0,
    "date": null,
    "weekDay": 0,
    "type": 0,
    "currentDate": false,
    "stringDate": "2016-09-07"
}, {
    "year": 0,
    "month": 0,
    "day": 0,
    "date": null,
    "weekDay": 0,
    "type": 0,
    "currentDate": false,
    "stringDate": "2016-09-07"
}, {
    "year": 116,
    "month": 9,
    "day": 1,
    "date": 1472659200000,
    "weekDay": 4,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-01"
}, {
    "year": 116,
    "month": 9,
    "day": 2,
    "date": 1472745600000,
    "weekDay": 5,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-02"
}, {
    "year": 116,
    "month": 9,
    "day": 3,
    "date": 1472832000000,
    "weekDay": 6,
    "type": 3,
    "currentDate": false,
    "stringDate": "2016-09-03"
}, {
    "year": 116,
    "month": 9,
    "day": 4,
    "date": 1472918400000,
    "weekDay": 0,
    "type": 3,
    "currentDate": false,
    "stringDate": "2016-09-04"
}], [{
    "year": 116,
    "month": 9,
    "day": 5,
    "date": 1473004800000,
    "weekDay": 1,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-05"
}, {
    "year": 116,
    "month": 9,
    "day": 6,
    "date": 1473091200000,
    "weekDay": 2,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-06"
}, {
    "year": 116,
    "month": 9,
    "day": 7,
    "date": 1473177600000,
    "weekDay": 3,
    "type": 1,
    "currentDate": true,
    "stringDate": "2016-09-07"
}, {
    "year": 116,
    "month": 9,
    "day": 8,
    "date": 1473264000000,
    "weekDay": 4,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-08"
}, {
    "year": 116,
    "month": 9,
    "day": 9,
    "date": 1473350400000,
    "weekDay": 5,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-09"
}, {
    "year": 116,
    "month": 9,
    "day": 10,
    "date": 1473436800000,
    "weekDay": 6,
    "type": 3,
    "currentDate": false,
    "stringDate": "2016-09-10"
}, {
    "year": 116,
    "month": 9,
    "day": 11,
    "date": 1473523200000,
    "weekDay": 0,
    "type": 3,
    "currentDate": false,
    "stringDate": "2016-09-11"
}], [{
    "year": 116,
    "month": 9,
    "day": 12,
    "date": 1473609600000,
    "weekDay": 1,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-12"
}, {
    "year": 116,
    "month": 9,
    "day": 13,
    "date": 1473696000000,
    "weekDay": 2,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-13"
}, {
    "year": 116,
    "month": 9,
    "day": 14,
    "date": 1473782400000,
    "weekDay": 3,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-14"
}, {
    "year": 116,
    "month": 9,
    "day": 15,
    "date": 1473868800000,
    "weekDay": 4,
    "type": 4,
    "currentDate": false,
    "stringDate": "2016-09-15"
}, {
    "year": 116,
    "month": 9,
    "day": 16,
    "date": 1473955200000,
    "weekDay": 5,
    "type": 4,
    "currentDate": false,
    "stringDate": "2016-09-16"
}, {
    "year": 116,
    "month": 9,
    "day": 17,
    "date": 1474041600000,
    "weekDay": 6,
    "type": 4,
    "currentDate": false,
    "stringDate": "2016-09-17"
}, {
    "year": 116,
    "month": 9,
    "day": 18,
    "date": 1474128000000,
    "weekDay": 0,
    "type": 2,
    "currentDate": false,
    "stringDate": "2016-09-18"
}], [{
    "year": 116,
    "month": 9,
    "day": 19,
    "date": 1474214400000,
    "weekDay": 1,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-19"
}, {
    "year": 116,
    "month": 9,
    "day": 20,
    "date": 1474300800000,
    "weekDay": 2,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-20"
}, {
    "year": 116,
    "month": 9,
    "day": 21,
    "date": 1474387200000,
    "weekDay": 3,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-21"
}, {
    "year": 116,
    "month": 9,
    "day": 22,
    "date": 1474473600000,
    "weekDay": 4,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-22"
}, {
    "year": 116,
    "month": 9,
    "day": 23,
    "date": 1474560000000,
    "weekDay": 5,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-23"
}, {
    "year": 116,
    "month": 9,
    "day": 24,
    "date": 1474646400000,
    "weekDay": 6,
    "type": 3,
    "currentDate": false,
    "stringDate": "2016-09-24"
}, {
    "year": 116,
    "month": 9,
    "day": 25,
    "date": 1474732800000,
    "weekDay": 0,
    "type": 3,
    "currentDate": false,
    "stringDate": "2016-09-25"
}], [{
    "year": 116,
    "month": 9,
    "day": 26,
    "date": 1474819200000,
    "weekDay": 1,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-26"
}, {
    "year": 116,
    "month": 9,
    "day": 27,
    "date": 1474905600000,
    "weekDay": 2,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-27"
}, {
    "year": 116,
    "month": 9,
    "day": 28,
    "date": 1474992000000,
    "weekDay": 3,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-28"
}, {
    "year": 116,
    "month": 9,
    "day": 29,
    "date": 1475078400000,
    "weekDay": 4,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-29"
}, {
    "year": 116,
    "month": 9,
    "day": 30,
    "date": 1475164800000,
    "weekDay": 5,
    "type": 1,
    "currentDate": false,
    "stringDate": "2016-09-30"
}]];

var VacationDate = [{"year": "2016", "date": "2016-09-15", "type": 1}, {
    "year": "2016",
    "date": "2016-09-16",
    "type": 1
}, {"year": "2016", "date": "2016-09-17", "type": 1}, {
    "year": "2016",
    "date": "2016-09-18",
    "type": 2
}, {"year": "2016", "date": "2016-10-01", "type": 1}, {
    "year": "2016",
    "date": "2016-10-02",
    "type": 1
}, {"year": "2016", "date": "2016-10-03", "type": 1}, {
    "year": "2016",
    "date": "2016-10-04",
    "type": 1
}, {"year": "2016", "date": "2016-10-05", "type": 1}, {
    "year": "2016",
    "date": "2016-10-06",
    "type": 1
}, {"year": "2016", "date": "2016-10-07", "type": 1}, {
    "year": "2016",
    "date": "2016-10-08",
    "type": 2
}, {"year": "2016", "date": "2016-10-09", "type": 2}];

var Epics = [{"epicName": "Test1", "odooProject": "TestProject1", "active": true}, {
    "epicName": "Test2",
    "odooProject": "TestProject2",
    "active": true
}, {"epicName": "Test3", "odooProject": "TestProject3", "active": true}, {
    "epicName": "Test4",
    "odooProject": "TestProject4",
    "active": true
}, {"epicName": "Test5", "odooProject": "TestProject5", "active": true}];

var Users = [{
    "name": "User1",
    "account": "user1",
    "password": "111111",
    "defaultTask": "Testing",
    "customs": {"active": false, "projects": [{"project": null, "task": null, "hours": 0.0}]},
    "date": null
}, {
    "name": "User2",
    "account": "user2",
    "password": "111111",
    "defaultTask": "Build",
    "customs": {"active": false, "projects": [{"project": null, "task": null, "hours": 0.0}]},
    "date": null
}, {
    "name": "User3",
    "account": "user3",
    "password": "111111",
    "defaultTask": "Plan",
    "customs": {
        "active": true,
        "projects": [{"project": "TestProject1", "task": "Build", "hours": 4.0}, {
            "project": "TestProject2",
            "task": "Plan",
            "hours": 4.0
        }]
    },
    "date": null
}, {
    "name": "User4",
    "account": "user4",
    "password": "111111",
    "defaultTask": "Build",
    "customs": {"active": false, "projects": [{"project": null, "task": null, "hours": 0.0}]},
    "date": null
}];