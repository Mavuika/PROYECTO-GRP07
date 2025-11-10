# Git Flow para PROYECTO-GRP07

Resumen rápido
- main (producción): código desplegado / releases etiquetados.
- master: copia de main creada (si tu equipo la usa).
- develop: rama de integración para la próxima versión.
- feature/*: nuevas funcionalidades (basadas en develop).
- release/*: preparación de versión (basadas en develop).
- hotfix/*: correcciones urgentes en producción (basadas en main).

Convenciones
- feature/NN-descripcion
- fix/NN-descripcion
- release/x.y.z
- hotfix/x.y.z

Flujos (resumen de comandos)
- Crear feature:
  git checkout develop
  git pull origin develop
  git checkout -b feature/123-descripcion
  [commits]
  git push -u origin feature/123-descripcion
  Abrir PR → develop

- Crear release:
  git checkout develop
  git checkout -b release/1.2.0
  Actualizar versión y tests
  Abrir PR release/1.2.0 → main
  Merge a main, tag: git tag -a v1.2.0 -m "v1.2.0"
  git push origin main --tags
  git checkout develop
  git merge --no-ff main

- Hotfix:
  git checkout main
  git checkout -b hotfix/1.2.1
  Corregir y push
  Abrir PR hotfix/1.2.1 → main
  Merge, tag y merge-back a develop

Pull Request policy
- feature → develop
- release/hotfix → main (luego merge a develop)
- Requisitos: CI verde, 1-2 revisores, descripción clara

Protecciones recomendadas (GitHub)
- Proteger main y develop:
  - Requerir PRs aprobados
  - Requerir checks de CI exitosos
  - Bloquear force-push/borrado

Versionado
- Semantic Versioning: vMAJOR.MINOR.PATCH

Checklist antes de merge
- CI verde
- Tests actualizados
- Documentación/changelog actualizado
