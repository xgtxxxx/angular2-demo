import {Account} from "./Account";
import {TimeSheetTask} from "./TimeSheetTask";
export class User {
    public id : string;
    public name : string;
    public account : Account;
    public authority : string;
    public mailAddress : string;
    public timesheetTasks : TimeSheetTask[]
    constructor() {  }
}
