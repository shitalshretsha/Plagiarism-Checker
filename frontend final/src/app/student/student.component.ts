import { Component, OnInit } from '@angular/core';
import { OnuploadService } from '../onupload.service';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {
  selectedFile: File = null;
  fileText: any;
  name: string;
  std_id: number;
  showMsg: boolean = false;

  constructor(private uploadService: OnuploadService) { }

  ngOnInit() {
  }

  onFileSelected(event) {
    this.selectedFile = <File>event.target.files[0];
    var reader = new FileReader();
    reader.readAsText(event.srcElement.files[0]);
    let that = this;
    reader.onload = function () {
      console.log(reader.result);
      that.fileText = reader.result;
    }
  }
  OnSubmit() {
    console.log(this.std_id);
    console.log(this.name); 
    this.uploadService.NewDocument(this.selectedFile, this.selectedFile.name, this.name,this.std_id).subscribe(res => {
      console.log(res);
      this.showMsg= true;
    }, err => {
      console.log(err);
      alert("Assignment Submitted successfully!!");
    });
  }

}
