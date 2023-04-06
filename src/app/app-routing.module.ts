import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VerUnaNoticiaComponent } from './componente/ver-una-noticia/ver-una-noticia.component';
import { VistainicioComponent } from './componente/vistainicio/vistainicio.component';
import { ListarComponent } from './compontente/listar/listar.component';
import { NoticiaFormComponent } from './componente/noticia-form/noticia-form.component';
import { RegistroComponent } from './componente/seguridad/registro.component';

const routes: Routes = [
  {path:"noticia",component:VistainicioComponent},
  {path:"verNoticia/:id",component:VerUnaNoticiaComponent},
  {path:"listar",component:ListarComponent},
  {path:'noticia/form',component:NoticiaFormComponent},
  {path:'noticia/form/:id',component:NoticiaFormComponent},
  {path:'usuario/form/:id',component:RegistroComponent},
  {path:'usuario/form',component:RegistroComponent},

]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }