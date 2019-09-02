export class Name {
    filename : string;
    studentname : String;
    std_id : number;
   

    constructor(filename?: string,studentname?:string,std_id?:number){
        this.filename = this.filename ? this.filename : '';
        this.studentname = this.studentname ? this.studentname : '';
        this.std_id =  std_id ? std_id : null;
      
    }
}
