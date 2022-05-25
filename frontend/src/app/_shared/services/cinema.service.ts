import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Cinema } from '../models/cinema';
import { map } from 'rxjs/operators';
import { Salle } from '../models/salle';
import { Ville } from '../models/ville';

@Injectable({
  providedIn: 'root'
})
export class CinemaService {

  private Checkout = 'http://localhost:8080/payerTickets';
  imageUrl = 'http://localhost:8080/imageFilm/';

  public host:String = "http://localhost:8080"
  constructor(private http: HttpClient) { }

  public getVilles() : Observable<any>{
    return this.http.get(this.host+"/villes");
  }
  getCinemas(ville){
    return this.http.get(ville._links.cinemas.href);
  }
  getSallesByCinema(cinema){
    return this.http.get(cinema._links.salle.href);
  }
  getProjectionsBySalle(salle){
    let url= salle._links.projections.href.replace("{?projection}","");
    return this.http.get(url+"?projection=p1");
  }
  getTicketsPlacesForAprojection(p){
    let url:string = p._links.tickets.href.replace("{?projection}","");
    return this.http.get(url+"?projection=ticketProj");
  }
  payerTickets(formData){
    return this.http.post(this.Checkout, formData);
  }
}