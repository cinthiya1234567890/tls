import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getCascaderParams } from 'app/entities/cascader-param/cascader-param.reducer';
import { ICascader } from 'app/shared/model/cascader.model';
import { getEntities as getCascaders } from 'app/entities/cascader/cascader.reducer';
import { getEntity, updateEntity, createEntity, reset } from './cascader-param.reducer';
import { ICascaderParam } from 'app/shared/model/cascader-param.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICascaderParamUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CascaderParamUpdate = (props: ICascaderParamUpdateProps) => {
  const [childId, setChildId] = useState('0');
  const [parentId, setParentId] = useState('0');
  const [cascaderId, setCascaderId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { cascaderParamEntity, cascaderParams, cascaders, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/cascader-param');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCascaderParams();
    props.getCascaders();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...cascaderParamEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="matriSearchApp.cascaderParam.home.createOrEditLabel">Create or edit a CascaderParam</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cascaderParamEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="cascader-param-id">ID</Label>
                  <AvInput id="cascader-param-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="paramTitleLabel" for="cascader-param-paramTitle">
                  Param Title
                </Label>
                <AvField id="cascader-param-paramTitle" type="text" name="paramTitle" />
              </AvGroup>
              <AvGroup>
                <Label id="peckOrderLabel" for="cascader-param-peckOrder">
                  Peck Order
                </Label>
                <AvField id="cascader-param-peckOrder" type="string" className="form-control" name="peckOrder" />
              </AvGroup>
              <AvGroup>
                <Label id="levelIndexLabel" for="cascader-param-levelIndex">
                  Level Index
                </Label>
                <AvField id="cascader-param-levelIndex" type="string" className="form-control" name="levelIndex" />
              </AvGroup>
              <AvGroup>
                <Label for="cascader-param-parent">Parent</Label>
                <AvInput id="cascader-param-parent" type="select" className="form-control" name="parentId">
                  <option value="" key="0" />
                  {cascaderParams
                    ? cascaderParams.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="cascader-param-cascader">Cascader</Label>
                <AvInput id="cascader-param-cascader" type="select" className="form-control" name="cascaderId">
                  <option value="" key="0" />
                  {cascaders
                    ? cascaders.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.cascaderName}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/cascader-param" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  cascaderParams: storeState.cascaderParam.entities,
  cascaders: storeState.cascader.entities,
  cascaderParamEntity: storeState.cascaderParam.entity,
  loading: storeState.cascaderParam.loading,
  updating: storeState.cascaderParam.updating,
  updateSuccess: storeState.cascaderParam.updateSuccess,
});

const mapDispatchToProps = {
  getCascaderParams,
  getCascaders,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CascaderParamUpdate);
