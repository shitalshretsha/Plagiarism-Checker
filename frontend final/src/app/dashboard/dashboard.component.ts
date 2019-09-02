import { Component, OnInit } from '@angular/core';
import { Name } from '../name';
import { HttpClient } from '@angular/common/http';
import { OnuploadService } from '../onupload.service';
import { Router } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  selectedFile: File = null;
  plagPercentage: any;
  plag: any = '';

  nameObject = new Name();
  nameList: Name[];

  constructor(private http: HttpClient, private uploadService: OnuploadService, private router: Router) { }

  ngOnInit() {
    this.nameObject = new Name();
    this.loadAll();
  }

  loadAll() {
    this.uploadService.getAll().subscribe((res: Name[]) => {
      console.log(res);
      this.nameList = res;
      console.log(this.nameList)
      this.nameObject = new Name();
    })
  }

  delete(id: number) {
    var txt;
    var r = confirm("Are you sure you want to delete?");
    if (r == true) {
      txt = "You pressed OK!";
      this.uploadService.delete(id).subscribe(res => {
        this.loadAll();
      })
    } else {
      txt = "You pressed Cancel!";
      this.loadAll();
    }

  }
  logout() {
    this.router.navigate(['login']);
  }

  compare(id) {
    this.uploadService.compare(id).subscribe(res => {
      console.log(res);
      this.plag = res.plag;
      this.plagPercentage = res.percentage;
    }, err => {
      console.log(err);
    });
  }

}
