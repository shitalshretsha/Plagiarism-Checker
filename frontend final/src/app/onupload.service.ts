import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Name } from './name';

@Injectable({
  providedIn: 'root'
})
export class OnuploadService {


  content: any;

  base_url = 'http://localhost:8080';



  constructor(private http: HttpClient) { }

  onUpload(selectedFile, selectedFileName): Observable<any> {
    const fd = new FormData();
    fd.append('file', selectedFile, selectedFileName);
    return this.http.post(this.base_url + '/upload', fd);
  }

  NewDocument(selectedFile, selectedFileName, name,std_id): Observable<any> {
    const fd = new FormData();
    fd.append('file', selectedFile);
     fd.append('name',name);
     fd.append('stdId',std_id); 
    return this.http.post(this.base_url + '/file', fd);
  }

  getAll() {
    return this.http.get(this.base_url);
  }

  delete(id: number): Observable<any> {
    console.log("this is id", id);

    return this.http.delete(this.base_url + "/" + id)
  }

  compare(id: number): Observable<any>{
    return this.http.get(this.base_url + "/" + id);
  }
}
