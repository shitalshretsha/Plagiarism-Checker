import { Component, OnInit } from '@angular/core';
import { OnuploadService } from '../onupload.service';
import { HttpClient } from '@angular/common/http';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
 

  flag: boolean = false;
  selectedFile: File = null;
  fileText: any;
  plagPercentage: any;
  plag: any = '';
  constructor(private http: HttpClient, private uploadService: OnuploadService) { }


  onFileSelected(event) {
    this.selectedFile = <File>event.target.files[0];
    // console.log(this.selectedFile)

    var reader = new FileReader();
    reader.readAsText(event.srcElement.files[0]);
    let that = this;
    reader.onload = function () {
      console.log(reader.result);
      that.fileText = reader.result;
    }
  }
 


  onUpload() {
    this.uploadService.onUpload(this.selectedFile, this.selectedFile.name).subscribe(res => {
      this.flag = true;
      console.log(res);
      this.plag = res.plag;
      this.plagPercentage = res.percentage;
    }, err => {
      console.log(err);
    });
  }
  flagOff() {

    this.flag = false;
  }

}
