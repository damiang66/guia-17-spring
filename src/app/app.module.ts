import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './componente/navbar/navbar.component';
import { VistainicioComponent } from './componente/vistainicio/vistainicio.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { VerUnaNoticiaComponent } from './componente/ver-una-noticia/ver-una-noticia.component';
import { ListarComponent } from './compontente/listar/listar.component';
import { NoticiaFormComponent } from './componente/noticia-form/noticia-form.component';
import { FotoComponent } from './componente/foto/foto.component';
import { RegistroComponent } from './componente/seguridad/registro.component';
import { LogginComponent } from './componente/seguridad/loggin.component';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { AuthInterceptor } from './interceptors/auth.interceptor';
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    VistainicioComponent,
    VerUnaNoticiaComponent,
    ListarComponent,
    NoticiaFormComponent,
    FotoComponent,
    RegistroComponent,
    LogginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
